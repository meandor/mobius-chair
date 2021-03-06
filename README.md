# Mobius Chair (JVM)

[![CircleCI](https://circleci.com/gh/DiscoverAI/mobius-chair-jvm.svg?style=svg)](https://circleci.com/gh/DiscoverAI/mobius-chair-jvm)
[![GitHub license](https://img.shields.io/github/license/discoverai/mobius-chair-jvm.svg)](https://github.com/DiscoverAI/mobius-chair-jvm/blob/master/LICENSE)
[![release](https://maven-badges.herokuapp.com/maven-central/com.github.meandor/mobius-chair/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.meandor/mobius-chair) 

> The Mobius Chair is Metron's technological masterpiece and allows him to cross space-time and interdimensional barriers.
>
> -- http://dc.wikia.com/wiki/Mobius_Chair

A Scala library for versioning data (datasets, models) on an HDFS. This can be used by JVM applications.

## Testing
Execute the tests with gradle:
```bash
./bin/go check
```

## Building
To build with gradle:
```bash
./bin/go clean build
```
This will create a jar file in `./build/libs` without the dependencies.

## Releasing and Publishing
```bash
./bin/go release
```
Will release a newer version with semantic versioning 

```bash
./bin/go publish
```

will publish the artifact to maven central.
