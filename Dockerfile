FROM byrnedo/alpine-curl
COPY ./entrypoint.sh /root/entrypoint.sh
RUN chmod u+x /root/entrypoint.sh
WORKDIR /root
ENTRYPOINT [ "./entrypoint.sh" ]
