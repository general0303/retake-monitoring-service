FROM golang:1.16-alpine
WORKDIR /app
COPY go.mod ./
RUN go mod download
COPY main.go ./
COPY ui ./ui
RUN go build -o /docker-gs-ping
CMD [ "/docker-gs-ping" ]