# Run this app

```
mvn clean spring-boot:run
```

Debug at port 5005

# See the presentation:

```sh
ruby -run -ehttpd . -p8000
```

Then open your browser on [http://localhost:8000/Slides.html](http://localhost:8000/Slides.html)

# Known security issues in this app :)

## [XSS](https://www.owasp.org/index.php/Cross-site_Scripting_%28XSS%29)
Because we don't HTML escape content from users

## [Session cookie](https://www.owasp.org/index.php/HttpOnly)
Because session cookie is not flagged with `HttpOnly`, it can be fetched
  using XSS.

## [CSRF](https://www.owasp.org/index.php/Cross-Site_Request_Forgery_%28CSRF%29)
Because we don't have any csrf tokens on this site

## [Clickjacking](https://www.owasp.org/index.php/Clickjacking) and more

Because we have not set any response headers like

```
X-Frame-Options:SAMEORIGIN
X-Permitted-Cross-Domain-Policies:master-only
X-XSS-Protection:1;mode=block
Content-Security-Policy: script-src 'self'
```




## Using `GET` request for logout
Basically CSRF: Any other site can link to the logout url and make a user
log out without wanting it. Not very _dangerous_ though :)

## The login
- It's just bogus, you can type any phone number in the login box. This is a
  demo app, so login is not the point :)
