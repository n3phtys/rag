
# R.A.G. (Random Asset Generator)

This kotlin-based CLI tool takes a json in a custom Template syntax and uses random generators to multiply the given json. It can deal with simple JSON arrays or even nested structures.

## JSON Template Syntax

The template must be valid json. Use Json arrays syntax where multiplication should happen.

There are so called placeholder strings, which are normal json-string-fields. They are recognized by a leading `$`. To be exact, at the moment everything besides the placeholder in this string value will be thrown away. This may change in later versions. Following placeholders are supported at the moment:

`$name` will be replaced by a (full) name for a person. We have a hardcoded list for first names and one for surnames. If you want to be able to use custom lists, please create an issue or a pull request - we didn't encounter this use case so far.

`$id_int` will be replaced by a unique integer (careful: this changes the type of the json primitive from string to number!). The ids are only unique per single program run.

`$id_int_bla_bla_bla` allows you to have an id-category called 'bla_bla_bla'. Ids of this category will only be unique within this category. This can be used when different types of ids should appear inside the same JSON.

`$id_str` does the same as its integer-cousin, but keeps the json primitive's type as string. Internally it will just print the integer as a string.



### Not yet implemented:

`$ref_id_int_bla_bla_bla` which will allow you to reference a generated unique id of category "bla_bla_bla"


## How to run tests
`./gradlew test`

## How to develop
`./gradlew installDist && ./build/install/rag/bin/rag` compiles and runs the CLI tool in one line, and can be run with parameters for simple access


## How to distribute

`./gradlew distZip` will create a zip with shell and batch scripts to start the application from command line. The zip lies at `./build/distributions`