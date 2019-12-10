# conditional-jackson-property-filter
An extension of the Jackson serialisation filter framework which provides the ability to add conditional logic,  through SpEL, to dynamically suppress fields serialised into json.

[![CircleCI](https://circleci.com/gh/johnhunsley/conditional-jackson-property-filter.svg?style=svg)](https://circleci.com/gh/johnhunsley/conditional-jackson-property-filter) 
[![](https://jitpack.io/v/johnhunsley/conditional-jackson-property-filter.svg)](https://jitpack.io/#johnhunsley/conditional-jackson-property-filter)

## Usage 

1. Enable the SpEL filter by annotating your application configuration or main class with the @EnableJacksonSpELFilter. 
This will add an ObjectMapper bean tot he context, if there isn't one already, and add an instance of the SpELPropertyFilterImpl
named 'spel'.
2. Annotate the type in which the SpEL Filter will be applied with the JsonFilter annotation - @JsonFilter('spel').
3. Annotate the fields within the class declared in 2, which are required to be conditionally suppressed, with the 
JsonFilterExpression annotation and declare the expression
which will be evaluated to decide whether to suppress the field in json output. 