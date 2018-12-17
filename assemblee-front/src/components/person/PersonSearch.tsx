import * as React from 'react';
import Api from 'src/api/api';
import PersonSearchItem from 'src/model/PersonSearchItem';
import {SelectedPerson} from 'src/components/person/SelectedPerson';
/* tslint:disable-next-line:no-var-requires 
this module does not have the Typescript typings file so we must use "require" */
const Autocomplete = require("react-autocomplete") as any;

interface IPersonSearchState {
    person: PersonSearchItem[],
    term: string,
    selectedPerson?: PersonSearchItem
}

export default class PersonSearch extends React.Component<{}, IPersonSearchState> {

    constructor(props: any){
        super(props);
        this.state = { 
            person: [],
            term: ''
        };
    }

    public render() {
        return (
            <div>
                <div className="field">
                    <label htmlFor="person-search-input" className="label">Par nom de famille : </label>
                    
                    <Autocomplete
                        wrapperProps={{className: "control"}}
                        inputProps={{ name: 'person-search-input', className: "input" }}
                        value={this.state.term}
                        items={this.state.person}
                        onChange={this.onInputChange}
                        onSelect={this.onInputSelect}
                        getItemValue={this.renderAutocompleteItemValue}
                        renderMenu={this.renderAutocompleteItems}
                        renderItem={this.renderAutocompleteItem}
                    />
                </div>

                <SelectedPerson selectedPerson={this.state.selectedPerson} />
            </div>
        )
    }

    private onInputChange = (event: Event, value: string) => {
        this.setState({
            term: value
        });

        if(value.length > 1) {
            Api.searchPersonByLastName(value)
                .then(response => {
                    this.setState({
                        person: response.data,
                    });
                })
                .catch(error => {
                    // TODO handle error
                    alert(error);
                });
        } else {
            this.setState({
                person: [],
            });
        }
    }

    private onInputSelect = (value: string, item: PersonSearchItem) => {
        this.setState({
            selectedPerson: item
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