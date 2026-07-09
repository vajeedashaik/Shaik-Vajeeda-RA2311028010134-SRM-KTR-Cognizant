Exercise 1: HTTP Request Response

To get a granular level of details about HTTP Request and Response, follow the steps below:

1. Open the link https://tools.ietf.org/html/rfc7230 in browser. This document contains the standard definition for HTTP request response.
2. Refer sample HTTP request and response in page number 7. This is the actual bytes of data that is transferred between the browser and server.

Specific details about the request and response:

Request:
```
GET /hello.txt HTTP/1.1
User-Agent: curl/7.16.3 libcurl/7.16.3 OpenSSL/0.9.7l zlib/1.2.3
Host: www.example.com
Accept-Language: en, mi
```

- Line 1 contains: Method type - GET, Resource - /hello.txt, HTTP Version - HTTP/1.1
- Line 2 contains the details about the client
- Line 3 contains the server that will respond to this request
- The URL given in the browser is broken into Resource and Host in the HTTP Request

Response:
```
HTTP/1.1 200 OK
Date: Mon, 27 Jul 2009 12:28:53 GMT
Server: Apache
Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT
ETag: "34aa387-d-1568eb00"
Accept-Ranges: bytes
Content-Length: 51
Vary: Accept-Encoding
Content-Type: text/plain

Hello World! My payload includes a trailing CRLF.
```

- Line 1: HTTP Version - HTTP/1.1, Response Status - 200 (this means the request is responded successfully), Response Message - Contains the response message
- Line 2 - Date of request
- Line 9 - Type of content returned. There is a list of predefined Content-Types. Based on Content-Type browser decides how the content has to be visually displayed. Few examples below:
  - text/plain - Text content
  - text/html - HTML Document
  - application/json - JSON content
  - image/png - Image content of type PNG
- Last line contains the content of the resource.
  - In case of text/html, this will contain the HTML tags
  - In case of application/json, this will contain the JSON response
  - In case of image/png, this will contain the bytes to render the image

To view the request and response details in browser, follow the steps below:

1. Open Chrome Browser
2. Press F12 to open the Developer Tools
3. Go to 'Network' tab in Developer Tools
4. Open google search website in this browser window
5. Click on the first link available in the 'Network' tab
6. A new window will open in the right hand side. Observe the following details. It will contain 3 sections. The data displayed will be similar to the HTTP request, response given above.
   - General
   - Response Headers
   - Request Headers

NOTE: This is a conceptual/observational exercise (browser DevTools walkthrough) — it does not involve writing or changing any project code, so there is no source file to copy for this exercise.
