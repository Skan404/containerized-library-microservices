import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { BibliotekaService } from '../../services/biblioteka.service';

@Component({
  selector: 'app-book-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent implements OnInit {
  book: any = null;

  constructor(
    private bibliotekaService: BibliotekaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const bookId = this.route.snapshot.paramMap.get('bookId');
    if (bookId) {
      this.bibliotekaService.getElementById(bookId).subscribe(
        (book) => {
          this.book = book;
        },
        (error) => {
          console.error('Błąd podczas pobierania szczegółów książki:', error);
        }
      );
    }
  }

  goBack(): void {
    const categoryId = this.route.snapshot.paramMap.get('categoryId');
    this.router.navigate([`/categories/${categoryId}`]);
  }
}
