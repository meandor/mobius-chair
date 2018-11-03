# Mobius Chair

[![CircleCI](https://circleci.com/gh/meandor/mobius-chair.svg?style=svg)](https://circleci.com/gh/meandor/mobius-chair)
[![GitHub license](https://img.shields.io/github/license/meandor/mobius-chair.svg)](https://github.com/meandor/mobius-chair/blob/master/LICENSE)
[![release](https://maven-badges.herokuapp.com/maven-central/com.github.meandor/mobius-chair/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.meandor/mobius-chair) 

> The Mobius Chair is Metron's technological masterpiece and allows him to cross space-time and interdimensional barriers.
>
> -- http://dc.wikia.com/wiki/Mobius_Chair

A Scala library for versioning data (datasets, models) on an HDFS.

## Testing
Execute the tests with gradle:
```bash
./gradlew check
```

## Building
To build with gradle:
```bash
./gradlew clean build
```
This will create a jar file in `./build/libs` without the dependencies.

## Releasing and Publishing
```bash
./gradlew release
```
Will release a newer version with semantic versioning 

```bash
./gradlew publish
```

will publish the artifact to maven central.
