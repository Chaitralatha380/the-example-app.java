package com.contentful.tea.java.services.modelconverter;

import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;
import com.contentful.tea.java.models.courses.Category;
import com.contentful.tea.java.models.courses.Course;
import com.contentful.tea.java.models.courses.CoursesParameter;
import com.contentful.tea.java.services.localization.Keys;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.contentful.java.cda.image.ImageOption.http;

@Component
public class ArrayToCourses extends ContentfulModelToMappableTypeConverter<ArrayToCourses.ArrayAndSelectedCategory, CoursesParameter> {

  public static class ArrayAndSelectedCategory {
    private List<CDAResource> list;
    private String categorySlug;

    public List<CDAResource> getList() {
      return list;
    }

    public ArrayAndSelectedCategory setList(List<CDAResource> list) {
      this.list = list;
      return this;
    }

    public String getCategorySlug() {
      return categorySlug;
    }

    public ArrayAndSelectedCategory setCategorySlug(String categorySlug) {
      this.categorySlug = categorySlug;
      return this;
    }
  }

  @Override
  public CoursesParameter convert(ArrayAndSelectedCategory compound) {
    final String slug = compound.getCategorySlug() == null ? "" : compound.getCategorySlug();

    // find slugged category and filter out non slugged courses.
    CDAEntry sluggedCategory = null;
    final List<CDAResource> filteredCourses = new ArrayList<>();
    for (final CDAResource courseResource : compound.getList()) {
      final CDAEntry course = (CDAEntry) courseResource;
      final CDAEntry category = getCategoryBySlug(course, slug);
      if (category != null) {
        filteredCourses.add(course);
        sluggedCategory = category;
      }
    }

    final List<CDAResource> courses = slug.isEmpty() ? compound.getList() : filteredCourses;
    final String categoryName = sluggedCategory != null ? sluggedCategory.getField("title") : "";
    final String title = createTitle(categoryName, courses.size());

    final CoursesParameter parameter = new CoursesParameter();
    parameter
        .getBase()
        .getMeta()
        .setTitle(title)
        .setAllCoursesCssClass(slug.isEmpty() ? "active" : "")
    ;

    parameter
        .setCategories(createCategories(courses, slug))
        .setCourses(createCourses(courses))
        .setTitle(title)
    ;

    return parameter;
  }

  private String createTitle(String categoryName, int total) {
    return String.format("%s (%d)", categoryName.isEmpty() ? t(Keys.allCoursesLabel) : categoryName, total);
  }

  private List<Category> createCategories(List<CDAResource> courses, String selectedCategory) {
    final Map<String, Category> categories = new HashMap<>();

    for (final CDAResource resource : courses) {
      if (!(resource instanceof CDAEntry)) {
        throw new IllegalStateException("Courses found of non entry type");
      }

      final CDAEntry course = (CDAEntry) resource;
      final List<CDAEntry> cdaCategories = course.getField("categories");
      for (final CDAEntry category : cdaCategories) {
        final String slug = category.getField("slug");
        if (!categories.containsKey((String) slug)) {
          categories.put(
              slug,
              new Category()
                  .setSlug(slug)
                  .setTitle(category.getField("title"))
                  .setCssClass(selectedCategory.equals(slug) ? "active" : "")
          );
        }
      }
    }

    return new ArrayList<>(categories.values());
  }

  private List<Course> createCourses(List<CDAResource> cdaCourses) {
    final List<Course> courses = new ArrayList<>();

    for (final CDAResource resource : cdaCourses) {
      if (!(resource instanceof CDAEntry)) {
        throw new IllegalStateException("Courses found of non entry type");
      }

      final CDAEntry course = (CDAEntry) resource;
      final CDAAsset image = course.getField("image");
      final Course createdCourse = new Course()
          .setImageUrl(image.urlForImageWith(http()))
          .setTitle(course.getField("title"))
          .setShortDescription(course.getField("description"))
          .setSlug(course.getField("slug"));

      final List<CDAEntry> categories = course.getField("categories");
      for (final CDAEntry category : categories) {
        createdCourse.addCategory(
            new Category()
                .setSlug(category.getField("slug"))
                .setTitle(category.getField("title"))
        );
      }

      courses.add(createdCourse);
    }

    return courses;
  }

  private CDAEntry getCategoryBySlug(CDAEntry course, String slug) {
    final List<CDAEntry> categories = course.getField("categories");
    final List<CDAEntry> list = new ArrayList<>();
    for (final CDAEntry e : categories) {
      if (slug.equals(e.getField("slug"))) {
        list.add(e);
      }
    }
    return list.size() > 0 ? list.get(0) : null;
  }
}
