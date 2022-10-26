Feature: Example Http Requests with reqres API

Scenario: Get Request
  Given I create a "get" request
  When I run a "get" request
  Then the status returns "200"
  And I print the response array
  And the "per_page" from the response is "6"

Scenario Outline: Post Request
  Given I create a "post" request with table
    | Name   | Job   |
    | <name> | <job> |
  When I run a "post" request
  Then the status returns "201"
#  And the "id" from the response is "805"
  And I print from the response using key "id"

  Examples:
    | name     | job    |
    | morpheus | leader |

Scenario Outline: Put Request
  Given I create a "put" request with table
    | Name   | Job   |
    | <name> | <job> |
  When I run a "put" request
  Then the status returns "200"
  And I print from the response using key "updatedAt"

  Examples:
    | name     | job    |
    | morpheus | leader |

Scenario: Delete Request
  Given I create a "delete" request
  When I run a "delete" request
  Then the status returns "204"

