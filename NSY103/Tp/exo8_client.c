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
    int sockfd, sockserv, serv, client;  
    struct addrinfo hints, *svinfo, *res; 
    char buf[100];
    char   str[100];

    memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM; 
    serv = getaddrinfo(SERVEUR, PORTS, &hints, &svinfo);
    
    sockserv = socket(svinfo->ai_family, svinfo->ai_socktype, svinfo->ai_protocol);

    memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM; 
    hints.ai_flags = AI_PASSIVE;
    client = getaddrinfo(NULL, PORTC, &hints, &res);

    sockfd = socket(res->ai_family, res->ai_socktype, res->ai_protocol);

    bind(sockfd, res->ai_addr, res->ai_addrlen);

    printf ("Donnez moi deux nombres ? additionner:");
    scanf ("%s", str);

    connect(sockfd, svinfo->ai_addr, svinfo->ai_addrlen);
    send(sockserv, str, sizeof(str), 0);
    recv(sockfd, buf, 100, 0);
    printf("msg rcv est %s", buf );
    close(sockfd);
}
