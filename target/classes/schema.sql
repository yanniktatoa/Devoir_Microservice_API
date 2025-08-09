-- Supprimer les tables si elles existent (ordre important à cause des clés étrangères)
DROP TABLE IF EXISTS teams;
DROP TABLE IF EXISTS departments;

-- Créer la table departments
CREATE TABLE departments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

-- Créer la table teams
CREATE TABLE teams (
    id INT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(100) NOT NULL,
    description TEXT,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

-- Insérer les départements
INSERT INTO departments (name) VALUES 
('Direction Générale'),
('Ressources Humaines'),
('Finance'),
('Informatique'),
('Marketing'),
('Commercial'),
('Production'),
('Logistique');

-- Insérer les équipes (assurez-vous que les department_id correspondent bien)
INSERT INTO teams (team_name, description, department_id) VALUES
('Équipe Direction', 'Responsable de la stratégie globale de l’entreprise.', 1),
('Équipe RH 1', 'Gestion des recrutements et des relations employés.', 2),
('Équipe RH 2', 'Formation et développement du personnel.', 2),
('Équipe Finance 1', 'Comptabilité générale et reporting.', 3),
('Équipe Finance 2', 'Gestion de la trésorerie.', 3),
('Équipe Informatique 1', 'Développement des applications internes.', 4),
('Équipe Informatique 2', 'Maintenance des systèmes et réseaux.', 4),
('Équipe Marketing 1', 'Stratégie de contenu et communication.', 5),
('Équipe Marketing 2', 'Gestion des campagnes publicitaires.', 5),
('Équipe Commerciale 1', 'Ventes B2B.', 6),
('Équipe Commerciale 2', 'Ventes B2C.', 6),
('Équipe Commerciale 3', 'Support client et fidélisation.', 6),
('Équipe Production 1', 'Planification de la production.', 7),
('Équipe Production 2', 'Gestion des machines et outils.', 7),
('Équipe Production 3', 'Contrôle qualité.', 7),
('Équipe Logistique 1', 'Gestion des stocks.', 8),
('Équipe Logistique 2', 'Organisation des livraisons.', 8),
('Équipe Logistique 3', 'Relations avec les transporteurs.', 8),
('Équipe Projet A', 'Coordination inter-départements.', 1),
('Équipe Projet B', 'Mise en œuvre des projets stratégiques.', 1),
('Équipe Projet C', 'Innovation et R&D.', 1),
('Équipe Partenariats', 'Relations avec les partenaires externes.', 5),
('Équipe Données', 'Analyse de données marketing.', 5),
('Équipe Sécurité', 'Sécurité informatique et conformité.', 4),
('Équipe Support IT', 'Assistance technique aux employés.', 4),
('Équipe Paie', 'Gestion des salaires.', 2),
('Équipe Audit', 'Audit interne et conformité.', 3),
('Équipe Facturation', 'Émission et suivi des factures.', 3);
