FROM node:13.12

RUN yarn global add @vue/cli
RUN yarn install

RUN mkdir app
COPY . /app
WORKDIR /app

EXPOSE 8080