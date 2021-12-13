Feature: Test Github API

  @tuyentest
  Scenario: The Github user can Create and Delete repository by API.
    Given I get Github owner ID
    When I create a Github repository
    When I update a Github repository "tuyenrepo9"
    Then I delete repository


