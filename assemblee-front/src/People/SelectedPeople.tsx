import * as React from 'react';
import PersonSearchItem from 'src/Model/PersonSearchItem';

interface ISelectedPeopleProps {
    selectedPeople?: PersonSearchItem 
}

export const SelectedPeople: React.StatelessComponent<ISelectedPeopleProps> = (props) => {
    return (
        <div>
            {props.selectedPeople && <div>Mon député : {props.selectedPeople.fullName}</div>}
        </div>
    );
}