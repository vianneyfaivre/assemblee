import * as React from 'react';

import PeopleSearch from 'src/components/people/PeopleSearch';

/*
 * 
 *  TODO:
 * rename People to Person 
 * in SelectedPeople component, add person mandates
 */
 
export default class PersonHomePage extends React.Component {

    public render() {
        return (
            <section className="section">
                <div className="container">
                    <h2 className="title">
                        Rechercher mon député
                    </h2>

                    <PeopleSearch />
                </div>
            </section>
        )
    }
}