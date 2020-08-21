# Item information retrieval app

## How to run

For running this app you need to have installed `docker`, `maven` and `docker-compose`. Run it using the ***run.sh*** script as follows: `sh run.sh`

## Tools used

This app was developed in Java using the following libraries:

- [Apache Jersey](https://eclipse-ee4j.github.io/jersey/)
- [Maven](https://maven.apache.org/)
- [Vavr](https://www.vavr.io/)
- [gson](https://github.com/google/gson)
- [immutables](https://immutables.github.io/)
- [postgresql](https://www.postgresql.org/)
- [Apache tomcat](http://tomcat.apache.org/)
- [Junit 5](https://junit.org/junit5/docs/current/user-guide/)
- [mockito](https://site.mockito.org/)

## Requests and responses 

If you run this app in your local machine you can test the `/items` endpoint using

```bash 
~$ curl localhost:8080/items/MLU460998489
```
This is an example of the response
```json
{
  "id": "MLU460998489",
  "title": "Google Pixel 32gb Silver - Impecable!",
  "categoryId": "MLU1055",
  "price": 350,
  "startTime": "2019-03-02T20:31:02Z",
  "stopTime": "2019-10-25T23:28:35Z",
  "children": [
    {
      "itemId": "MLU468887129",
      "stopTime": "2020-04-25T22:10:52Z"
    }
  ]
}
```

You can also test the `/health` endpoint using

```sh
~$ curl localhost:8080/health
```
The response will be something like this
```json
{
  "date": "2020-08-21T05:36:55.480Z",
  "avg_response_time": 9,
  "total_reqeusts": 5,
  "info_request": [
    {
      "status_code": 200,
      "count": 9
    }
  ]
}
```

## App structure

The following are the main components

Component | Description
------------ | -------------
**db**           | Hasdb implementaion details: Connections, daos and repository implementations
**domain**       | Has the domain objects and repository interfaces
**json**         | Has the gson/jersey implementation
**rest**         | Has the clients, services and repository implementations
**App**          | Is the main class for the app.It is responsible for initializing all the components

## TODOS

- [ ] Rise the test coverage up to 85% at least
- [ ] Add a retry mechanism in the rest package (Maybe using [failsafe](https://jodah.net/failsafe/)
- [ ] Implementing async calls in both **db** and **rest** components
- [ ] Add a little more sofisticated error handling layer
- [ ] Complete `/health` endpoint

