import * as React from 'react';
import Api from 'src/Api/api';
import PersonSearchItem from 'src/Model/PersonSearchItem';
import {SelectedPeople} from 'src/People/SelectedPeople';
/* tslint:disable-next-line:no-var-requires 
this module does not have the Typescript typings file so we must use "require" */
const Autocomplete = require("react-autocomplete") as any;

/*
 * TODO
 * - faire un package "components"
 * - handle API call error
 * - API : create an ENV file
 * - clear search results
 * - Display more info about the user
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

    public render() {
        return (
            <div>
                <div className="field">
                    <label htmlFor="people-search-input" className="label">Par nom de famille : </label>
                    
                    <Autocomplete
                        wrapperProps={{className: "control"}}
                        inputProps={{ name: 'people-search-input', className: "input" }}
                        value={this.state.term}
                        items={this.state.people}
                        onChange={this.onInputChange}
                        onSelect={this.onInputSelect}
                        getItemValue={this.renderAutocompleteItemValue}
                        renderMenu={this.renderAutocompleteItems}
                        renderItem={this.renderAutocompleteItem}
                    />
                </div>

                <SelectedPeople selectedPeople={this.state.selectedPeople} />
            </div>
        )
    }

    private onInputChange = (event: Event, value: string) => {
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

    private onInputSelect = (value: string, item: PersonSearchItem) => {
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
    private renderAutocompleteItems(children: any[]) {

        if(children.length === 0) {
            return <span />;
        }

        return (
            <div className="box">
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
                className={`${isHighlighted ? 'has-background-light' : ''}`}
                key={item.id}
            >
                {item.lastName} {item.firstName} 
            </div>
        );
    }
}