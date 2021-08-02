import './App.css';
import React from 'react';
import axios from 'axios';
import Header from './Header';


class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      events: [],
      isLoaded: false,
    };
  }

  componentDidMount() {
    document.title = "Jogos Olímpicos Portugal"
    axios.get('http://localhost:8080/events/today')
      .then(result => {
        this.setState({
          isLoaded: true,
          events: result.data
        });
      });
  }

  render() {
    const items = this.state;
    var html;
    if (items.isLoaded) {
      html = <div>
        <Header />
          {items.events.map(event => <div>
          <h2>{event.day}</h2>
          <table class="container">
          <thead>
          <tr>
              <th><h1>Hora (PT-PT)</h1></th>
              <th><h1>Atleta</h1></th>
              <th><h1>Desporto</h1></th>
              <th><h1>Ronda</h1></th>
              <th><h1>Adversário</h1></th>
              <th><h1>Transmissão</h1></th>
          </tr>
          </thead>
          <tbody>
            {event.eventsForTheDay.map(eventDay => <tr>
              <td>{eventDay.portugalTime}</td>
              <td>{eventDay.athlete}</td>
              <td>{eventDay.sport}</td>
              <td>{eventDay.round}</td>
              <td>{eventDay.directOponent}</td>
              <td>{eventDay.trasmission}</td>
            </tr>)}
          </tbody>
          </table>
          </div>
          )}
      </div>
    } else {
      html = <div>
        <Header />
        <h1>A carregar dados...</h1>;
      </div>;
    }
    return html;
  }
}

export default App;
