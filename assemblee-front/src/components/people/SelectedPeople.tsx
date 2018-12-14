import * as React from 'react';
import PersonSearchItem from 'src/model/PersonSearchItem';

interface ISelectedPeopleProps {
    selectedPeople?: PersonSearchItem 
}

export const SelectedPeople: React.StatelessComponent<ISelectedPeopleProps> = (props) => {

    if(props.selectedPeople) {

        if(props.selectedPeople.gender === "FEMALE") {
            return <div>Ma députée : {props.selectedPeople.lastName} {props.selectedPeople.firstName}</div>;
        } else {
            return <div>Mon député : {props.selectedPeople.lastName} {props.selectedPeople.firstName}</div>;
        }
    }

    return <div />;
}