
export class Memento {

    private state: any;

    public constructor(state: any) {
        this.state = state;
    }

    public getState(): any {
        return this.state;
    }
}