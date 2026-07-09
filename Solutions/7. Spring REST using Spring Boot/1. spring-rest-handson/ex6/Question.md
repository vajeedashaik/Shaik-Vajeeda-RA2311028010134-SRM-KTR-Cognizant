Hands on 6: Spring Core – Load list of countries from Spring Configuration XML

Our main objective was to retrieve the list of four countries for the airlines website. Refer steps below to get this incorporated.

1. Create a separate bean for each of the four country in country.xml.
2. Create an ArrayList of Country in country.xml. Refer code below.

```xml
<bean id="countryList" class="java.util.ArrayList">
    <constructor-arg>
        <list>
            <ref bean="in"></ref>
            <ref bean="us"></ref>
            <ref bean="de"></ref>
            <ref bean="jp"></ref>
        </list>
    </constructor-arg>
</bean>
```

3. Include new method displayCountries() in SpringLearnApplication.java
4. In displayCountries() read the country list created above
5. Display the list of countries as debug log.

SME to provide detailing on below aspects:
- `<list>`
- `<ref>`
- bean attribute

IMPORTANT NOTE: Do not forget to include the start and end logs in this new method.
