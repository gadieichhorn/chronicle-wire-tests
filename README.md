# chronicle-wire-tests
using chronicle wire lib to test different ways to serialize objects more effectively in java 8

    clone and run 
    > maven clean install

Exploring different options to encode and decode objects. The natural java way is very expensive in terms of memory consumption, if you need to persist a complete object tree into memory (like a DB) the option to reduce the space is very interesting

1.  Serialising objects into:
  * BINARRY
  * TEXT
  * FIELDLESS_BINARY
  * Binarry
  * JSON

2. Serialising nested objects (Collections of other collections and properties)

3. Useing a base class


