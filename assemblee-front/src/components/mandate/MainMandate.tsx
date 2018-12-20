import * as React from 'react';
import Mandate from 'src/model/Mandate';
import { DatePeriodLabel } from 'src/components/mandate/DatePeriodLabel';

interface IMainMandateProps {
    mandate: Mandate
}

export const MainMandate : React.StatelessComponent<IMainMandateProps> = (props) => {

    return (
        <div>
            Député de l'{props.mandate.politicalBodyLabel}&nbsp;

            <DatePeriodLabel startDate={props.mandate.startDate} endDate={props.mandate.endDate} />
        </div>
    );
}