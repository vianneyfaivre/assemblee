import * as React from 'react';
import PersonSearchItem from 'src/model/PersonSearchItem';
import PersonMandates from 'src/model/PersonMandates';

interface ISelectedPersonProps {
    selectedPerson?: PersonSearchItem,
    mandates?: PersonMandates,
}

export const SelectedPerson: React.StatelessComponent<ISelectedPersonProps> = (props) => {

    if(props.selectedPerson && props.mandates) {

        const genderLabel = props.selectedPerson.gender === "FEMALE" ? 'Ma députée' : 'Mon député';
        const mainMandateEnded: boolean = props.mandates.mainMandate.endDate !== null;
        
        return (
            <div>
                <div>{genderLabel} : {props.selectedPerson.lastName} {props.selectedPerson.firstName}</div>

                <div>
                    Mandat principal: {props.mandates.mainMandate.politicalBodyLabel}&nbsp;

                    {mainMandateEnded && 
                        <span>(du {props.mandates.mainMandate.startDate} au {props.mandates.mainMandate.endDate})</span>
                    }

                    {!mainMandateEnded && 
                        <span>(depuis le {props.mandates.mainMandate.startDate})</span>
                    }
                </div>
            </div>
        );
    }

    return <div />;
}