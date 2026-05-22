ALTER TABLE movie
    ADD COLUMN director_id INTEGER,
    ADD CONSTRAINT fk_movie_director
    FOREIGN KEY (director_id) REFERENCES director(id);