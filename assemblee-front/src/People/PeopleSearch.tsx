import * as React from 'react';

class PeopleSearch extends React.Component {

    public render() {
        return (
            <div>
                <label htmlFor="people-search-input">Rechercher un député : </label>
                
                <input 
                    name="people-search-input"
                    type="text" />
            </div>
        )
    }
}

export default PeopleSearch;