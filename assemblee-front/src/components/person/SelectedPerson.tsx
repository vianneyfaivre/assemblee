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

        const otherMandatesTableRows = props.mandates.otherMandates.map((mandate) => {

            return (
                <tr key={mandate.mandateId}>
                    <td>{mandate.politicalBodyLabel}</td>
                    <td>{mandate.politicalBodyType}</td>
                    <td>
                        {mandate.endDate && <span>du {mandate.startDate.toLocaleDateString('fr-FR')} au {mandate.endDate.toLocaleDateString('fr-FR')}</span>}
                        {!mandate.endDate && <span>depuis le {mandate.startDate.toLocaleDateString('fr-FR')}</span>}
                    </td>
                </tr>
            )
        }
        );
        
        return (
            <div>
                <h2 className="title">{genderLabel} : {props.selectedPerson.lastName} {props.selectedPerson.firstName}</h2>

                <div>
                    Mandat principal à l'{props.mandates.mainMandate.politicalBodyLabel}&nbsp;

                    {mainMandateEnded && 
                        <span>(du {props.mandates.mainMandate.startDate.toLocaleDateString('fr-FR')} au {props.mandates.mainMandate.endDate!.toLocaleDateString('fr-FR')})</span>
                    }

                    {!mainMandateEnded && 
                        <span>(depuis le {props.mandates.mainMandate.startDate.toLocaleDateString('fr-FR')})</span>
                    }

                    <h3 className="title">Ses mandats : </h3>

                    <table className="table">

                        <thead>
                            <tr>
                                <th>Nom du mandat</th>
                                <th>Type de mandat</th>
                                <th>Période</th>
                            </tr>
                        </thead>

                        <tbody>
                            {otherMandatesTableRows}
                        </tbody>

                    </table>
                </div>
            </div>
        );
    }

    return <div />;
}