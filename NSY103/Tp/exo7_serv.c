#include <stdio.h> 
#include <stdlib.h> 
#include <unistd.h> 
#include <string.h> 
#include <sys/types.h> 
#include <sys/socket.h> 
#include <netinet/in.h> 
#include <netdb.h>

#define PORTS "55555"

int     main()
{
    int sockfd, new_fd, rv, sin_size; 
    pid_t numpid;
    struct addrinfo hints, *svinfo; 
    struct sockaddr their_addr;

    memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM; 
    hints.ai_flags = AI_PASSIVE;
    rv = getaddrinfo(NULL, PORTS, &hints, & svinfo);

    sockfd = socket(svinfo->ai_family, svinfo->ai_socktype, svinfo->ai_protocol);

    bind(sockfd, svinfo->ai_addr, svinfo->ai_addrlen);

    listen(sockfd, 5);

    while (1)
    {
        sin_size = sizeof(their_addr);
        new_fd = accept(sockfd, &their_addr, &sin_size); 
        numpid = fork();
        if (numpid==0)
        {
            close(sockfd);
            send(new_fd, "Hello!", 6, 0);
            close(new_fd); exit(0); 
        } 
        else
            close (new_fd); 
    }
}
