Playwright with Java
===
# 1. Introduction
- Playwright was created specifically to accommodate the needs of end-to-end testing.
- Playwright supports all modern rendering engines including Chromium, WebKit, and Firefox.
- Playwright is distributed as a set of Maven modules.
- The easiest way to use it is to add one dependency to project's pom.xml 

# 2. Setup

## 2.1 JAVA 
    Download: https://download.oracle.com/java/24/latest/jdk-24_windows-x64_bin.zip
- Add path for java
![img.png](image/img.png)
![img_1.png](image/img_1.png)
## 2.2 MAVEN
    Download: https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip
- Add path for Maven
![img_2.png](image/img_2.png)
![img_1.png](image/img_1.png)
## 2.3 IDE
    Download: https://www.jetbrains.com/idea/download/?section=windows
## 2.4 Add dependencies into pom.xml

    <dependencies>
        <dependency>
            <groupId>com.microsoft.playwright</groupId>
            <artifactId>playwright</artifactId>
            <version>1.47.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.11.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies> 
