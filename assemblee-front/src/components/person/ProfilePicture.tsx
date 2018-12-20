import * as React from 'react';
import PersonSearchItem from 'src/model/PersonSearchItem';

interface IProfilePictureProps {
    person: PersonSearchItem,
    legislature: number,
}

export const ProfilePicture: React.StatelessComponent<IProfilePictureProps> = (props) => {
     return (
        <img 
            src={`http://www2.assemblee-nationale.fr/static/tribun/${props.legislature}/photos/${props.person.id.split("PA")[1]}.jpg`} 
            alt={props.person.lastName + ' ' + props.person.firstName} />
     );
}