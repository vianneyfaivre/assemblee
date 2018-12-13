import axios, { AxiosPromise } from 'axios';
import PersonSearchItem from 'src/Model/PersonSearchItem';

class Api {

    /**
     * Returns a promise that contains a list of PersonSearchItem
     */
    public static searchPeopleByLastName(lastName: string): AxiosPromise<PersonSearchItem[]> {
        return axios.request<PersonSearchItem[]>(
            {
                method: 'get',
                params: {
                    "lastName": lastName
                },
                url: 'http://localhost:8080/persons/search',
            });
    }

}

export default Api;