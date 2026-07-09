Exercise 3: Understanding JWT

What is JWT?
- JWT stands for JSON Web Token
- Internet standard (IETF 7519) for creating JSON-based access tokens
- JWT can typically be used to pass identity of authenticated users and service provider

JWT Process Flow:
1. Client sends username and password to server
2. Server validates credentials, creates token (JWT) and responds with it
3. Client attaches the token in subsequent requests to server
4. Server validates the token (JWT) on each client request

Structure of JSON Web Token (Reference: https://en.wikipedia.org/wiki/JSON_Web_Token#Structure):
- Header: Contains the encryption algorithm
- Payload: Contains application specific data. Usually this contains the user id and role.
- Signature: Computed based on the formula defined using header and payload

Exercise to check how JWT token is created:
1. Open link https://en.wikipedia.org/wiki/JSON_Web_Token#Structure in browser
2. Open link https://jwt.io/ in another browser tab and scroll down to the Encoded, Decoded section
3. Copy and paste the header content from wikipedia article and paste it in header section of https://jwt.io
4. Copy and paste the payload content from wikipedia article and paste it in payload section of https://jwt.io
5. Type "secretkey" in the textbox within Verify Signature section
6. Check if the token generated in the Encoded section of https://jwt.io matches with the generated token displayed in the Structure section of wikipedia article

NOTE: This is a conceptual/observational exercise (learning JWT structure via jwt.io) — it does not involve writing or changing any project code, so there is no source file to copy for this exercise.
