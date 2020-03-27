import { Memento } from './memento'

export class Originator {
    private state: Object;
    public setState(state: Object) {
        this.state = state;
    }

    public commit(): Memento {
        return new Memento(this.state);
    }
    
    public roolback(m: Memento) {
        this.state = m.getState();
    }
}