#include <string.h> 
#include <stdlib.h> 
#include <unistd.h> 
#include <stdio.h> 
#include <sys/types.h> 
#include <sys/socket.h> 
#include <netinet/in.h> 
#include <netdb.h>

#define SERVEUR "127.0.0.1"
#define PORTS "55555"
#define PORTC "55559"

int     main()
{
    int sockfd, new_fd, serv, client, sin_size; 
    struct addrinfo hints, *svinfo, *res; 
    char buf[100];

    memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM; 
    serv = getaddrinfo(SERVEUR, PORTS, &hints, &svinfo);

    memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM; 
    hints.ai_flags = AI_PASSIVE;
    client = getaddrinfo(NULL, PORTC, &hints, &res);

    sockfd = socket(res->ai_family, res->ai_socktype, res->ai_protocol);

    bind(sockfd, res->ai_addr, res->ai_addrlen);

    connect(sockfd, svinfo->ai_addr, svinfo->ai_addrlen);
    recv(sockfd, buf, 100, 0);
    printf("msg rcv est %s\n", buf);
    close(sockfd);
}
