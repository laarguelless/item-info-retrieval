create table if not exists items
(id VARCHAR(250) primary key,
  title VARCHAR(250),
  categoryId VARCHAR(250),
  startTime timestamp ,
  stopTime timestamp
);