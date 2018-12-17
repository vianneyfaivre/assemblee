import * as React from 'react';
import PersonSearchItem from 'src/model/PersonSearchItem';

interface ISelectedPersonProps {
    selectedPerson?: PersonSearchItem 
}

export const SelectedPerson: React.StatelessComponent<ISelectedPersonProps> = (props) => {

    if(props.selectedPerson) {

        if(props.selectedPerson.gender === "FEMALE") {
            return <div>Ma députée : {props.selectedPerson.lastName} {props.selectedPerson.firstName}</div>;
        } else {
            return <div>Mon député : {props.selectedPerson.lastName} {props.selectedPerson.firstName}</div>;
        }
    }

    return <div />;
}