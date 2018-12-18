import * as React from 'react';
import Mandate from 'src/model/Mandate';

interface IMandateTableProps {
    title: string,
    mandates?: Mandate[],
}

export const MandateTable : React.StatelessComponent<IMandateTableProps> = (props) => {
    
    if(!props.mandates || props.mandates.length === 0) {
        return <div />;
    }

    const rows = props.mandates.map((mandate) => {
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
            <h3 className="title">{props.title} : </h3>

            <table className="table">

                <thead>
                    <tr>
                        <th>Nom du mandat</th>
                        <th>Type de mandat</th>
                        <th>Période</th>
                    </tr>
                </thead>

                <tbody>
                    {rows}
                </tbody>

            </table>
        </div>
    );
}