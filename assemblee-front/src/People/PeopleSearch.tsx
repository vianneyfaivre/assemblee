import * as React from 'react';
import Api from 'src/Api/api';
/* tslint:disable:no-var-requires */
// this module does not have the Typescript typings file so we must use "require"
const Autocomplete = require("react-autocomplete") as any;
/* tslint:enable:no-var-requires */
import PersonSearchItem from 'src/Model/PersonSearchItem';


/*
 * TODO
 * - clear search results
 * - handle call error
 * - API : create an ENV file
 * - find a solution for jsx-no-bind
 * - disable tslint rule on property order (for all files)
 * - new component for selected person
 */

interface IPeopleSearchState {
    people: PersonSearchItem[],
    term: string,
    selectedPeople?: PersonSearchItem
}

export default class PeopleSearch extends React.Component<{}, IPeopleSearchState> {

    constructor(props: any){
        super(props);
        this.state = { 
            people: [],
            term: ''
        };
    }

    /* tslint:disable:jsx-no-bind */

    public render() {
        return (
            <div>
                <div className="field">
                    <label htmlFor="people-search-input" className="label">Rechercher un député par nom de famille : </label>
                    
                    <Autocomplete
                        inputProps={{ id: 'people-search-input' }}
                        value={this.state.term}
                        items={this.state.people}
                        onChange={this.onInputChange.bind(this)}
                        onSelect={this.onInputSelect.bind(this)}
                        getItemValue={this.renderAutocompleteItemValue}
                        renderMenu={this.renderAutocompleteItems}
                        renderItem={this.renderAutocompleteItem}
                        wrapperProps={{className: "control"}}
                        renderInput={this.renderInput}
                    />
                </div>

                {this.state.selectedPeople && <div>Député sélectionné : {this.state.selectedPeople.fullName}</div>}

            </div>
        )
    }

    private onInputChange(event: Event, value: string) {
        this.setState({
            term: value
        });

        if(value.length > 1) {
            Api.searchPeopleByLastName(value)
                .then(response => {
                    this.setState({
                        people: response.data,
                    });
                })
                .catch(error => {
                    // TODO handle error
                    alert(error);
                });
        } else {
            this.setState({
                people: [],
            });
        }
    }

    private onInputSelect(value: string, item: PersonSearchItem) {
        this.setState({
            selectedPeople: item
        });
    }

    /**
     * String value that will be passed as "value" parameter in method #onInputSelect
     */
    private renderAutocompleteItemValue(item: PersonSearchItem): string {
        return item.id;
    }

    /**
     * DOM Wrapper element for all autocomplete items
     */
    private renderAutocompleteItems(children: any) {
        return (
            <div className="menu">
                {children}
            </div>
        );
    }

    /**
     * Dom renderer for each autocomplete item
     */
    private renderAutocompleteItem(item: PersonSearchItem, isHighlighted: boolean) {
        return (
            <div
                className={`item ${isHighlighted ? 'item-highlighted' : ''}`}
                key={item.id}
            >
                {item.fullName}
            </div>
        );
    }

    private renderInput(props: any) {
        return <input {...props} className="input" />
    }
}