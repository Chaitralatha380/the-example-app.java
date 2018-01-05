package com.contentful.tea.java.utils;

import com.contentful.tea.java.models.base.BaseParameter;
import com.contentful.tea.java.models.base.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtils {
  public static BaseParameter createBaseParameter() {
    final BaseParameter base = new BaseParameter();
    base.getApi()
        .setApiSwitcherHelp("TEST-setApiSwitcherHelp")
        .setCdaButtonCSSClass("TEST-setCdaButtonCSSClass")
        .setContentDeliveryApiHelp("TEST-setContentDeliveryApiHelp")
        .setContentDeliveryApiLabel("TEST-setContentDeliveryApiLabel")
        .setContentPreviewApiHelp("TEST-setContentPreviewApiHelp")
        .setContentPreviewApiLabel("TEST-setContentPreviewApiLabel")
        .setCpaButtonCSSClass("TEST-setCpaButtonCSSClass")
        .setCurrentApiId("TEST-setCurrentApiId")
        .setCurrentApiLabel("TEST-setCurrentApiLabel")
    ;

    base.getMeta()
        .setCategoriesLabel("TEST-setCategoriesLabel")
        .setContactUsLabel("TEST-setContactUsLabel")
        .setCoursesCSSClass("TEST-setCoursesCSSClass")
        .setCoursesLabel("TEST-setCoursesLabel")
        .setCurrentPath("TEST-setCurrentPath")
        .setDescription("TEST-setDescription")
        .setDraftLabel("TEST-setDraftLabel")
        .setFooterDisclaimer("TEST-setFooterDisclaimer")
        .setHomeCSSClass("TEST-setHomeCSSClass")
        .setHomeLabel("TEST-setHomeLabel")
        .setImageAlt("TEST-setImageAlt")
        .setImageDescription("TEST-setImageDescription")
        .setImprintLabel("TEST-setImprintLabel")
        .setLogoAlt("TEST-setLogoAlt")
        .setModalCTALabel("TEST-setModalCTALabel")
        .setModalIntro("TEST-setModalIntro")
        .setModalSpaceIntro("TEST-setModalSpaceIntro")
        .setModalSpaceLinkLabel("TEST-setModalSpaceLinkLabel")
        .setModalTitle("TEST-setModalTitle")
        .setModalPlatforms("TEST-setModalPlatforms")
        .setPendingChangesLabel("TEST-setPendingChangesLabel")
        .setQueryString("TEST-setQueryString")
        .setSettingsLabel("TEST-setSettingsLabel")
        .setTitle("TEST-setTitle")
        .setTwitterCard("TEST-setTwitterCard")
        .setUpperMenuCSSClass("TEST-setUpperMenuCSSClass")
        .setViewOnGitHub("TEST-setViewOnGitHub")
        .setWhatIsThisApp("TEST-setWhatIsThisApp")
        .setComingSoonLabel("TEST-comingSoonLabel")
        .setHostedLabel("TEST-hostedLabel")
    ;

    base.getLocales()
        .setCurrentLocaleCode("TEST-setCurrentLocaleCode")
        .setCurrentLocaleName("TEST-setCurrentLocaleName")
        .setLocaleQuestion("TEST-setLocaleQuestion")
        .setLocaleLabel("TEST-setLocaleLabel")
        .addLocale(
            new Locale()
                .setCode("en-US")
                .setName("\uD83C\uDDFA\uD83C\uDDF8")
                .setCssClass("inactive")
        )
        .addLocale(
            new Locale()
                .setCode("de-DE")
                .setName("\uD83C\uDDE9\uD83C\uDDEA")
                .setCssClass("active")
        );
    return base;
  }

  public static void assertBaseParameterInHtml(String generatedHtml) {
    assertThat(generatedHtml)
        .doesNotContain("\uD83D\uDE31")
        .doesNotContain("!{")
        .doesNotContain("#{")
        .contains("<meta property=\"og:url\" content=\"http://contentful-example-app-java.herokuapp.com/TEST-setCurrentPath\">")
        .contains("\uD83C\uDDE9\uD83C\uDDEA")
        .contains("\uD83C\uDDFA\uD83C\uDDF8")
        .contains("active")
        .contains("de-DE")
        .contains("inactive")
        .contains("TEST-setApiSwitcherHelp")
        .contains("TEST-setCdaButtonCSSClass")
        .contains("TEST-setContactUsLabel")
        .contains("TEST-setContentDeliveryApiHelp")
        .contains("TEST-setContentDeliveryApiLabel")
        .contains("TEST-setContentPreviewApiHelp")
        .contains("TEST-setContentPreviewApiLabel")
        .contains("TEST-setCoursesCSSClass")
        .contains("TEST-setCoursesLabel")
        .contains("TEST-setCpaButtonCSSClass")
        .contains("TEST-setCurrentApiId")
        .contains("TEST-setCurrentApiLabel")
        .contains("TEST-setCurrentLocaleCode")
        .contains("TEST-setCurrentLocaleName")
        .contains("TEST-setCurrentPath")
        .contains("TEST-setDescription")
        .contains("TEST-setFooterDisclaimer")
        .contains("TEST-setHomeCSSClass")
        .contains("TEST-setHomeLabel")
        .contains("TEST-setImageAlt")
        .contains("TEST-setImageDescription")
        .contains("TEST-setImprintLabel")
        .contains("TEST-setLocaleQuestion")
        .contains("TEST-setLogoAlt")
        .contains("TEST-setModalCTALabel")
        .contains("TEST-setModalIntro")
        .contains("TEST-setModalSpaceIntro")
        .contains("TEST-setModalSpaceLinkLabel")
        .contains("TEST-setModalTitle")
        .contains("TEST-setModalPlatforms")
        .contains("TEST-setQueryString")
        .contains("TEST-setSettingsLabel")
        .contains("TEST-setTitle")
        .contains("TEST-setTwitterCard")
        .contains("TEST-setUpperMenuCSSClass")
        .contains("TEST-setViewOnGitHub")
        .contains("TEST-setWhatIsThisApp")
        .contains("TEST-comingSoonLabel")
        .contains("TEST-hostedLabel")
    ;
  }
}