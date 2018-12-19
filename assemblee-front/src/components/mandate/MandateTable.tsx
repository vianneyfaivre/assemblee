import * as React from 'react';
import {Link} from 'react-router-dom';
import { DatePeriodLabel } from 'src/components/mandate/DatePeriodLabel';
import MandateGrouped from 'src/model/MandateGrouped';

interface IMandateTableProps {
    title: string,
    mandatesGrouped?: MandateGrouped[],
}

export const MandateTable : React.StatelessComponent<IMandateTableProps> = (props) => {
    
    if(!props.mandatesGrouped || props.mandatesGrouped.length === 0) {
        return <div />;
    }
    
    const rows = props.mandatesGrouped.map((mandateGrouped) => {

        const mandates = mandateGrouped.mandates.map((mandate) => {
            const qualitySuffix= mandate.quality && (mandate.quality.endsWith('du') || mandate.quality.endsWith('au')) ? 'groupe' : '';
    
            return (
                <div key={mandate.mandateId}>
                    {mandate.quality} {qualitySuffix} <DatePeriodLabel startDate={mandate.startDate} endDate={mandate.endDate} />
                </div>
            );
        });

        return (
            <tr key={mandateGrouped.politicalBodyId}>
                <td>
                    <div style={{maxWidth: '600px'}}>
                        <Link to={'/organes/'+mandateGrouped.politicalBodyId}>
                            {mandateGrouped.politicalBodyLabel} 
                            {mandateGrouped.legislature > 0 && <span> ({mandateGrouped.legislature}è législature)</span>}
                        </Link>
                    </div>
                </td>
                <td>
                    {mandates}
                </td>
            </tr>
        )
     }
    );

    return (
        <div style={{marginTop: '50px'}}>
            <h3 className="subtitle">{props.title} : </h3>

            <table className="table is-bordered is-narrow is-hoverable is-striped">

                <thead>
                    <tr>
                        <th>Nom du mandat</th>
                        <th>Rôles</th>
                    </tr>
                </thead>

                <tbody>
                    {rows}
                </tbody>

            </table>
        </div>
    );
}