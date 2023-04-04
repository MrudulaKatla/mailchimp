Feature: Register

  Scenario Outline: Register Mailchimp
    Given I have started a "<Browser>"
    And I have given an "<Email>"
    And I have entered the "<Username>"
    And I have entered  "<Password>"
    When I click on Signup button
    Then The user is "<registered>"
    Examples:
      | Browser | Email             | Username                                                                                                                                                   | Password     | registered |
      | chrome  | mrudula@mnbvc.co  | abcdefgh                                                                                                                                                   | Mrudula@123  | yes        |
      | edge    | mrudula@mnbvc.com | abcdefgh                                                                                                                                                   | Abcdefghij1? | yes        |
      | chrome  | mrudula@mnbvc.com | abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234 | Abcdefghij1? | no         |
      | edge    | mrudula@mnbvc.com | abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234abcdefghij1234 | Abcdefghij1? | NO         |
      | chrome  |                   | abcdefgh                                                                                                                                                   | zxcvbnmj=A2  | NO         |