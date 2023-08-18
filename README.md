# CrowdProj project

This is an experimental project to create an Open Source social network aka Marketplace in a highly modular style.

## Roadmap

| Group / Subsystem | Microservice                                                                  | Purpose                                                                                                                                                                                  | Contributors                                   | Completeness | 
|-------------------|-------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------|--------------|
| Ads               | [Ads](https://github.com/crowdproj/crowdproj-ad)                              | User ads control. Users dispose texd descriptions of their demands or supplies to be met one another                                                                                     | [@svok](https://github.com/svok)               | 20%          |
| Product           |                                                                               |                                                                                                                                                                                          |                                                |              |
|                   | [Product Group](https://github.com/crowdproj/crowdproj-product-groups)        | Microservice for description of a products group. For instance, group "Refrigerators" has specific properties like color, weight, minimum temperature, etc.                              | [@phaeton03](https://github.com/phaeton03)     | 75%          |
|                   | [Product Model](https://github.com/crowdproj/crowdproj-product-models)        | Microservice for description of a product model. For instance, model "Refrigerator M-123" has specific values of the properties like weight is 30 kg, minimum temperature is -20 C, etc. | [@azatgt1996](https://github.com/azatgt1996)   |              |
|                   | [Product Property](https://github.com/crowdproj/crowdproj-product-properties) | Microservice for managing product properties. Properties have name, description, units of measure, etc.                                                                                  | [@Riafka](https://github.com/Riafka)           | 70%          |
|                   | [Product Property Value]()                                                    |                                                                                                                                                                                          |                                                |              |
| References        | [Units](https://github.com/crowdproj/crowdproj-units)                         |                                                                                                                                                                                          | [@fooglish](https://github.com/fooglish)       | 70%          |
| Comments          | [Comment](https://github.com/crowdproj/crowdproj-comments)                    |                                                                                                                                                                                          | [@StellaLupus](https://github.com/StellaLupus) |              |
| Ratings           | [Ratings](https://github.com/crowdproj/crowdproj-ratings)                     |                                                                                                                                                                                          | [@shvetson](https://github.com/shvetson)       | 75%          |
|                   | [Vote](https://github.com/crowdproj/crowdproj-ratings-votes)                  |                                                                                                                                                                                          |                                                |              |
| Resource planner  |                                                                               | Calendar allows to plan access to some limited resources like computers, labour, time, etc.                                                                                              |                                                |              |
|                   | [Resources](https://github.com/crowdproj/crowdproj-resources)                 | Reference for the available resources                                                                                                                                                    | [@Zuzichev](https://github.com/Zuzichev)       |              |
|                   | [Resource Schedule Template]()                                                | Timeslots distribution over time, including specification of the working days, holidays and weekends                                                                                     |                                                |              |
|                   | [Resource Bookings]()                                                         | Available and booked timeslots must be controlled by this entity                                                                                                                         |                                                |              |
|                   | [Events]()                                                                    | Event specifies the detail about the booking for the resources and lists the timeslots for the resources                                                                                 |                                                |              |
| Library           |                                                                               | Library is a subsystem for management of different resources: video, music, documents, custom files, etc.                                                                                |                                                |              |
|                   | [Documents]()                                                                 | Reference of stored documents                                                                                                                                                            |                                                |              |
|                   | [Stored Resources]()                                                          | Resources attached to the document                                                                                                                                                       |                                                |              |
| Tags              | [Tags](https://github.com/crowdproj/crowdproj-tags)                           | Tags management and provisioning to other services                                                                                                                                       | [@dpopkov](https://github.com/dpopkov)         |              |

## Structure

### Component diagram

![Component diagram of the CrowdProj](imgs/crowdproj-components.drawio.png)

### Entities

![ER diagram of the CrowdProj](imgs/crowdproj-ER.drawio.png)

## Contacts

Telegram groups for discussions:

Development questions: [crowdproj-dev](https://t.me/crowdproj_dev)

Startup questions: [crowdproj-com](https://t.me/crowdproj_com)
