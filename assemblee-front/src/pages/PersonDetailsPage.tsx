import * as React from 'react';
import Api from 'src/api/api';
import PersonMandates from 'src/model/PersonMandates';
import { SelectedPerson } from 'src/components/person/SelectedPerson';
import PersonSearchItem from 'src/model/PersonSearchItem';

interface IPersonDetailsState {
    personId: string,
    selectedPerson?: PersonSearchItem,
    selectedPersonMandates?: PersonMandates
}

export default class PersonDetailsPage extends React.Component<{}, IPersonDetailsState> {

    constructor(props: any){
        super(props);

        // pathName == /depute/:personId (see routing in App.tsx)
        // => parts: 0='' 1=depute 2=:personId
        const pathNameParts: string[] = location.pathname.split('/');

        this.state = {
            personId: pathNameParts[2]
        }
    }

    public componentDidMount() {
        Api.getPersonById(this.state.personId)
            .then(response => {
                this.setState({
                    selectedPerson: response.data,
                });
            })
            .catch(error => {
                // TODO handle error
                alert(error);
            });
        
        Api.getPersonMandates(this.state.personId)
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

    public render() {
        return (
            <section className="section">
                <div className="container">
                    {this.state.selectedPerson && this.state.selectedPersonMandates && 
                        <SelectedPerson 
                            selectedPerson={this.state.selectedPerson}
                            mandates={this.state.selectedPersonMandates} 
                        />
                    }
                </div>
            </section>
          );
    }
}