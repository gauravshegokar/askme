import { Memento } from './memento'

export class Caretaker {

    private mementos = [];

    public addMemento(m: Memento) {
        this.mementos.push(m);
    }

    public getMemento(index): Memento {

        if (this.mementos.length == 1) {
            return this.mementos[0]
        }

        if (index == 0) {
            console.log(this.mementos[index])
            return this.mementos[index];
        }
        else {
            console.log("here")
            let as = this.mementos.pop()
            console.log(as)
            return as
        }
    }
}
