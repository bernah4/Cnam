#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>

int main()
{
    int ret;
    ret = fork();
    if (ret == 0)
        printf("Je suis le fils de PiD %d, mon pere est %d.\n",getpid(),getppid());
    else
    {
        printf("Je suis le pere de PiD %d, le grand pere est %d.\n",getpid(),getppid());
        wait(0);
    }
    return (0);
}
