FROM hseeberger/scala-sbt:8u312_1.6.1_3.1.0

RUN mkdir pm-scraper-logs

ENV LOG_FILES_DIR /pm-scraper-logs

ADD . /pm-scraper/

WORKDIR /pm-scraper

RUN apt-get update && \
    apt-get --assume-yes install chromium && \
    apt-get --assume-yes install chromium-driver

RUN mv /usr/bin/chromium /usr/bin/google-chrome-stable

RUN sbt compile

RUN sbt test

CMD ["sbt", "run"]
