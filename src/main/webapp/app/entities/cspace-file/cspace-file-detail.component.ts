import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICspaceFile } from 'app/shared/model/cspace-file.model';

@Component({
  selector: 'jhi-cspace-file-detail',
  templateUrl: './cspace-file-detail.component.html'
})
export class CspaceFileDetailComponent implements OnInit {
  cspaceFile: ICspaceFile;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ cspaceFile }) => {
      this.cspaceFile = cspaceFile;
    });
  }

  previousState() {
    window.history.back();
  }
}
