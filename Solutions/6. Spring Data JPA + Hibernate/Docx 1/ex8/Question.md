Hands on 8
Update a country based on code  
Create a new method updateCountry() in CountryService with parameters code and name. Annotate this method with @Transactional. Implement following steps in this method.
Get the reference of the country using findById() method in repository
In the country reference obtained, update the name of country using setter method
Call countryRepository.save() method to update the name
Include new test method in OrmLearnApplication, which invokes updateCountry() method in CountryService passing a country's code and different name for the country.
Check in database table if name is modified.
