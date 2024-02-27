import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { User } from './model/user';

@Component({
  standalone: true,
  selector: 'app-root',
  imports: [RouterOutlet, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  user: User = new User();

  constructor(private http: HttpClient) {
    http
      .get<User>('http://localhost:8080/home')
      .subscribe(data => this.user = data);
  }
}
