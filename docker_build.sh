#!/bin/zsh

# Build the docker image
podman build --build-arg RECEIPT_USER=$RECEIPT_USER --build-arg RECEIPT_PASSWORD=$RECEIPT_PASSWORD -t ghcr.io/preinpost/recepit .

# Log in to the GitHub Container Registry
echo $CR_PAT | podman login ghcr.io -u preinpost --password-stdin
#
## Push the docker image to the GitHub Container Registry
podman push ghcr.io/preinpost/recepit