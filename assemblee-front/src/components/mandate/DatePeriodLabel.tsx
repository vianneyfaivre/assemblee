import * as React from 'react';

interface IDatePeriodLabelProps {
    startDate: Date,
    endDate?: Date
}

export const DatePeriodLabel : React.StatelessComponent<IDatePeriodLabelProps> = (props) => {

    return (
        <span>
            {props.endDate && <span>du {props.startDate.toLocaleDateString('fr-FR')} au {props.endDate.toLocaleDateString('fr-FR')}</span>}
            {!props.endDate && <span>depuis le {props.startDate.toLocaleDateString('fr-FR')}</span>}
        </span>
    );
}