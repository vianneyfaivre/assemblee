import * as React from 'react';

import PersonSearch from 'src/components/person/PersonSearch';
import PersonSearchItem from 'src/model/PersonSearchItem';
import PersonMandates from 'src/model/PersonMandates';
import { SelectedPerson } from './SelectedPerson';
import Api from 'src/api/api';

interface IPersonHomePageState {
    selectedPerson?: PersonSearchItem,
    selectedPersonMandates?: PersonMandates
}

export default class PersonHomePage extends React.Component<{}, IPersonHomePageState> {

    constructor(props: any){
        super(props);
        this.state = {};
    }

    public render() {
        return (
            <section className="section">
                <div className="container">

                    {!this.state.selectedPerson && 
                        <PersonSearch 
                            onPersonSelect={this.onPersonSelect}
                        />
                    }

                    {this.state.selectedPerson && 
                        <SelectedPerson 
                            selectedPerson={this.state.selectedPerson}
                            mandates={this.state.selectedPersonMandates} 
                        />
                    }
                </div>
            </section>
        )
    }

    /**
     * Update state when a person has been selected
     * Also, retrieve mandates of that person.
     */
    private onPersonSelect = (value: string, selectedPerson: PersonSearchItem) => {
        this.setState({
            'selectedPerson': selectedPerson
        });

        Api.getPersonMandates(selectedPerson.id)
                .then(response => {
                    this.setState({
                        selectedPersonMandates: response.data,
                    });
                })
                .catch(error => {
                    // TODO handle error
                    alert(error);
                });
    }
}